package cc.zhanyun.repository;

import cc.zhanyun.model.location.Location;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public abstract interface LocationRepository
        extends MongoRepository<Location, String> {
    @Query(fields = "{'_id':1,'name':1,'address':1}")
    public abstract List<Location> findByUid(String paramString,Pageable pageable);
}

