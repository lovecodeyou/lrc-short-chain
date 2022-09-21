package work.linruchang.lrcshortchain.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 链接信息
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/19
 * @since 1.8
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName
public class LinkInfo extends Model<LinkInfo> implements Serializable {

    private static final long serialVersionUID = -280569011406024072L;
    /**
     * 主键
     */
    Long id;

    /**
     * 唯一标识
     */
    String uuid;

    /**
     * 原始链接
     */
    String sourceLink;

    /**
     * 短链接
     */
    String shortLink;

    /**
     * 描述
     */
    @TableField("`desc`")
    String desc;

    /**
     * 所属用户
     */
    @TableField("`user`")
    String user;

    @TableField(fill = FieldFill.INSERT)
    Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    Date updateTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    Integer isDel;

    String sourceUserIp;

}
