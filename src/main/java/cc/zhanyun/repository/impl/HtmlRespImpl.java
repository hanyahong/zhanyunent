package cc.zhanyun.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cc.zhanyun.model.html.Html;
import cc.zhanyun.repository.HtmlRepository;

@Repository
public class HtmlRespImpl {

    @Autowired
    private HtmlRepository htmlRepository;

    // 单条保存
    public void addHtml(Html html) {
        htmlRepository.save(html);
    }

    // 单条查询
    public Html selHtmlByOfferOid(String offeroid) {
        return htmlRepository.findByOfferoid(offeroid);
    }

}
