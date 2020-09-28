package codes.nttuan.paging;

import codes.nttuan.sorting.Sortable;

public interface Pageable {
    int getOffset();
    int getLimit();
    void setOffset(int offset);
    void setLimit(int limit);
    Sortable getSortable();
}
