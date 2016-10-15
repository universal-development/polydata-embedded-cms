package com.unidev.polyembeddedcms;

/**
 * Query object for new new polys
 */
public class PolyQuery {

    public static final Long DEFAULT_ITEM_PER_PAGE = 30L;

    private String tag;
    private String category;
    private Long page = 0L;

    private Long itemPerPage = DEFAULT_ITEM_PER_PAGE;

    public PolyQuery() {}

    public PolyQuery(String tag, String category, Long page) {
        this.tag = tag;
        this.category = category;
        this.page = page;
    }

    public static PolyQuery query() {
        return new PolyQuery();
    }

    public <T extends PolyQuery> T tag(String tag) {
        setTag(tag);
        return (T) this;
    }

    public <T extends PolyQuery> T category(String category) {
        setCategory(category);
        return (T) this;
    }

    public Long page() {
        return page;
    }

    public <T extends PolyQuery> T page(Long page) {
        setPage(page);
        return (T) this;
    }

    public <T extends PolyQuery> T itemPerPage(Long itemPerPage) {
        this.itemPerPage = itemPerPage;
        return (T) this;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(Long itemPerPage) {
        this.itemPerPage = itemPerPage;
    }
}
