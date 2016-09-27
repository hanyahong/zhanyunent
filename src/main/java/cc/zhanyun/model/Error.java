
package cc.zhanyun.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


@ApiModel(description = "")
public class Error {
    private Integer code = null;
    private String message = null;
    private String fields = null;


    @ApiModelProperty("")

    @JsonProperty("code")
    public Integer getCode() {

        return this.code;

    }


    public void setCode(Integer code) {
        this.code = code;
    }


    @ApiModelProperty("")

    @JsonProperty("message")
    public String getMessage() {

        return this.message;

    }


    public void setMessage(String message) {
        this.message = message;
    }


    @ApiModelProperty("")

    @JsonProperty("fields")
    public String getFields() {

        return this.fields;

    }


    public void setFields(String fields) {
        this.fields = fields;
    }


    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if ((o == null) || (getClass() != o.getClass())) {

            return false;

        }

        Error error = (Error) o;


        return (Objects.equals(this.code, error.code)) && (Objects.equals(this.message, error.message)) && (Objects.equals(this.fields, error.fields));

    }


    public int hashCode() {

        return Objects.hash(new Object[]{this.code, this.message, this.fields});

    }


    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("class Error {\n");


        sb.append("  code: ").append(this.code).append("\n");

        sb.append("  message: ").append(this.message).append("\n");

        sb.append("  fields: ").append(this.fields).append("\n");

        sb.append("}\n");

        return sb.toString();

    }

}


