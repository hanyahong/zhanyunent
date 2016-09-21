package cc.zhanyun.repository;

import cc.zhanyun.model.PageableInfo;
import cc.zhanyun.model.image.Image;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface ImageRepository
        extends MongoRepository<Image, String> {
    public abstract List<Image> findByUid(String paramString, Pageable pageable);
}
