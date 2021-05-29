package com.qzy.utils;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Page<T> implements Serializable {

    /**
     * 分页索引
     */
    private Integer index;

    /**
     * 当前页
     */
    private Integer pageNumber;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Integer totalCount;
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 搜索条件
     */
    private Map<String, Object> params = new HashMap<>(8);

    /**
     * 排序列
     */
    private String sortColumn;

    /**
     * 排序方式
     */
    private String sortMethod = "asc";

    public Integer getPageNumber() {
        if (this.pageNumber == null) {
            return 1;
        }
        return this.pageNumber;
    }

    public Integer getPageSize() {
        if (this.pageSize == null) {
            return 20;
        }
        return this.pageSize;
    }

    public Integer getIndex() {
        return (pageNumber - 1) * pageSize;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.totalPage = ( int ) Math.ceil(totalCount * 1.0 / getPageSize());
    }

}
