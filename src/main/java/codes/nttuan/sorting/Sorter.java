package codes.nttuan.sorting;

public class Sorter implements Sortable {
    private String sortBy;
    private String sortType;

    public Sorter(String sortBy, String sortType) {
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    @Override
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public String getSortBy() {
        return this.sortBy;
    }

    @Override
    public String getSortType() {
        return this.sortType;
    }
}
