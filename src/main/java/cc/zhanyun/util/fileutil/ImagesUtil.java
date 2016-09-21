package cc.zhanyun.util.fileutil;

import cc.zhanyun.model.location.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class ImagesUtil {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Integer addImage(Images images, String oid, String filed, String classes) {
        String dd = "Location.class";
        try {
            Query query = Query.query(Criteria.where("_id").is(oid));
            Update update = new Update();
            update.addToSet(filed, images);
            this.mongoTemplate.upsert(query, update, dd);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }


    public Integer removeImage(String oid, String imageoid, String filed1, String filed2, String classes) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid).and(filed1)
                    .is(imageoid));
            Update update = new Update();
            update.unset(filed2 + ".$");
            this.mongoTemplate.updateFirst(query, update, classes);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }
}

