package work.linruchang.lrcshortchain.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class LinkInfo implements Serializable {

    private static final long serialVersionUID = -280569011406024072L;
    /**
     * 主键
     */
    Long id;

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
    String desc;

    /**
     * 所属用户
     */
    String user;

}
