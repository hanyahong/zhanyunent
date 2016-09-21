package cc.zhanyun.repository.impl;

import cc.zhanyun.model.image.Image;
import cc.zhanyun.model.image.ImageProperty;
import cc.zhanyun.repository.ImageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepoImpl {
    @Autowired
    private ImageRepository ImageRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void addImage(Image image) {
        this.ImageRepo.save(image);
    }

    public void delImage(String oid, String ioid) {
        Query query = Query.query(Criteria.where("_id").is(oid)
                .and("property._id").is(ioid));
        Update update = new Update();
        update.pull("property", ".$");
        this.mongoTemplate.updateFirst(query, update, Image.class);
    }

    public Image selImage(String oid) {
        return (Image) this.ImageRepo.findOne(oid);
    }

    public List<Image> selImageByUid(String uid, Pageable pageable) {
        return this.ImageRepo.findByUid(uid, pageable);
    }

    public Integer addImageOne(ImageProperty property, String oid) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid));
            Update update = new Update();
            update.addToSet("property", property);
            this.mongoTemplate.upsert(query, update, Image.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }

    public Integer removeLocationImage(String oid, String imageoid) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid)
                    .and("image.imageProperty._id").is(imageoid));
            Update update = new Update();
            update.unset("image.$");
            this.mongoTemplate.updateFirst(query, update, Image.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }
}
