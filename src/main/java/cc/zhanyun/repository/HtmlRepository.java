package cc.zhanyun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cc.zhanyun.model.html.Html;

public interface HtmlRepository extends MongoRepository<Html, String> {

    public abstract Html findByOfferoid(String offeroid);
}
