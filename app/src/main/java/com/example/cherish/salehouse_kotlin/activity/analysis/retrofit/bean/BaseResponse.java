package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean;

/**
 * 基类
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 8:55
 */

public class BaseResponse<T> {
    private int RtnCode;
    private String RtnMsg;
    private T content;
    private PageData Page;

    public int getRtnCode() {
        return RtnCode;
    }

    public void setRtnCode(int rtnCode) {
        RtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return RtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        RtnMsg = rtnMsg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public PageData getPage() {
        return Page;
    }

    public void setPage(PageData page) {
        Page = page;
    }

    private class PageData {
        private int PageIndex;
        private int PageSize;
        private int Rows;

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int pageIndex) {
            PageIndex = pageIndex;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int pageSize) {
            PageSize = pageSize;
        }

        public int getRows() {
            return Rows;
        }

        public void setRows(int rows) {
            Rows = rows;
        }
    }
}
