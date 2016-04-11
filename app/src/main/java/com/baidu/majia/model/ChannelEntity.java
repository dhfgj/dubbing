package com.baidu.majia.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by wangyujie04 on 2016/3/1.
 */
public class ChannelEntity extends DataSupport {

    @Column(unique = true)
    public String tag;

    public String name;

    public ChannelEntity() {

    }

    public ChannelEntity(String tag , String name) {
        this.tag = tag;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ChannelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public ChannelEntity setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChannelEntity)) return false;
        final ChannelEntity other = (ChannelEntity) o;

        if (this.tag.equals(other.getTag()) && this.name.equals(other.getName()))
            return true;
        else
            return false;

    }

}
