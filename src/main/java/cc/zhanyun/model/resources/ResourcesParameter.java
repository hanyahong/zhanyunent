
package cc.zhanyun.model.resources;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.util.Objects;


@ApiModel(description = "")
public class ResourcesParameter {

    @Id
    private String oid = null;
    private String name = null;
    private String value = null;


    @ApiModelProperty("资源属性ID")

    @JsonProperty("oid")
    public String getOid() {

        return this.oid;

    }


    public void setOid(String oid) {

        this.oid = oid;

    }


    @ApiModelProperty("资源属性名称")

    @JsonProperty("name")
    public String getName() {

        return this.name;

    }


    public void setName(String name) {

        this.name = name;

    }


    @ApiModelProperty("资源属性值")

    @JsonProperty("value")
    public String getValue() {

        return this.value;

    }


    public void setValue(String value) {

        this.value = value;

    }


    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if ((o == null) || (getClass() != o.getClass())) {

            return false;

        }

        ResourcesParameter resourcesParameter = (ResourcesParameter) o;


        return (Objects.equals(this.oid, resourcesParameter.oid)) && (Objects.equals(this.name, resourcesParameter.name)) && (Objects.equals(this.value, resourcesParameter.value));

    }


    public int hashCode() {

        return Objects.hash(new Object[]{this.oid, this.name, this.value});

    }


    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("class ResoucesParameter {\n");


        sb.append("  oid: ").append(this.oid).append("\n");

        sb.append("  name: ").append(this.name).append("\n");

        sb.append("  value: ").append(this.value).append("\n");

        sb.append("}\n");

        return sb.toString();

    }

}


