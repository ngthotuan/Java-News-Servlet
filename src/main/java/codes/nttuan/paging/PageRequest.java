package codes.nttuan.paging;

import codes.nttuan.sorting.Sortable;

public class PageRequest implements Pageable {
    private int currentPage;
    private int limitItems;
    private Sortable sortable;

    public PageRequest(int currentPage, int limitItems, Sortable sortable){
        this.currentPage = currentPage;
        this.limitItems = limitItems;
        this.sortable = sortable;
    }

    @Override
    public int getOffset() {
        if(currentPage != 0 && limitItems != 0){
            return (currentPage -1)*limitItems;
        }
        return 0;
    }

    @Override
    public int getLimit() {
        return limitItems;
    }

    @Override
    public void setOffset(int offset) {
        if(this.limitItems != 0)
            this.currentPage = offset/limitItems + 1;
    }

    @Override
    public void setLimit(int limit) {
        this.limitItems = limit;
    }

    @Override
    public Sortable getSortable() {
        return this.sortable;
    }

    @Override
    public void setSortable(Sortable sortable) {
        this.sortable = sortable;
    }
}
