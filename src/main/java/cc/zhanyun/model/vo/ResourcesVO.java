
package cc.zhanyun.model.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;


@ApiModel(description = "")
public class ResourcesVO {

    @Id
    private String oid;
    private String simplename;
    private String classification;


    @ApiModelProperty("ID")

    @JsonProperty("oid")
    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    @ApiModelProperty("名称")

    @JsonProperty("simplename")
    public String getSimplename() {

        return this.simplename;

    }


    public void setSimplename(String simplename) {

        this.simplename = simplename;

    }


    @ApiModelProperty("分类")

    @JsonProperty("classification")
    public String getClassification() {

        return this.classification;

    }


    public void setClassification(String classification) {

        this.classification = classification;

    }

}


