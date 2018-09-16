package com.johnnyfivedev.domain.usecase.news;

public class GetNewsPageUseCaseParams {

    private int page;

    private int pageSize;


    public GetNewsPageUseCaseParams(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    //region ===================== Getters ======================

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    //endregion
}
