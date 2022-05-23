package com.albert.godworld.arm.resource.domain.social;

import lombok.Data;

import java.util.Date;

/**
 * SocialRequest 是社团管理员剋处理的社团请求，诸如：加入社团、退出社团...
 * 不可以做修改，只能创建、删除<br/>
 * message为肉眼可以观看的请求信息，包含直观的请求内容<br/>
 * 比如: <pre>
 *     AuthorName(社员)想申请参加活动 ActivityName, 参加书籍为 BookName
 *     AuthorName(非社员)想申请加入社团
 * </pre>
 * content则为请求的具体内容,以';'作为分割的一列属性键值<br/>
 * 比如:<pre>
 *     bookId: 1001; activityId: 1001
 * </pre>
 */
@Data
public class SocialRequest {

    private Long id;
    private Long socialId;
    private Long authorId;
    private Integer type;
    private String message;
    private String content;
    private Date createTime;
    private Boolean handled;
    private Long handleMemberId;
    private Boolean confirm;
}
