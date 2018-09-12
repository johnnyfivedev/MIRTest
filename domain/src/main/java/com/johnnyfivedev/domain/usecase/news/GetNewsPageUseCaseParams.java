package com.johnnyfivedev.domain.usecase.news;

public class GetNewsPageUseCaseParams {

    private int startPosition;

    private int pageSize;


    public GetNewsPageUseCaseParams(int startPosition, int pageSize) {
        this.startPosition = startPosition;
        this.pageSize = pageSize;
    }

    //region ===================== Getters ======================

    public int getStartPosition() {
        return startPosition;
    }

    public int getPageSize() {
        return pageSize;
    }

    //endregion
}
