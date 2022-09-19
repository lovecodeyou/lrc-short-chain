package work.linruchang.lrcshortchain.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author LinRuChang
 * @version 1.0
 * @date 2022/09/20
 * @since 1.8
 **/
@Component
public class FieldFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", () -> new Date(), Date.class);
        this.strictInsertFill(metaObject, "updateTime", () -> new Date(), Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", () -> new Date(), Date.class);
    }
}
