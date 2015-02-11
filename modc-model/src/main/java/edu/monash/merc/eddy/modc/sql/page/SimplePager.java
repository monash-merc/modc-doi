/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.sql.page;

/**
 * Created by simonyu on 27/08/2014.
 */
public class SimplePager implements IPaging {

    static final int DEFAULT_SIZE_PER_PAGE = 20;

    // total records in database
    protected int totalSize = 0;

    // display record size per page
    protected int sizePerPage = 20;

    // The current page number
    protected int pageNo = 1;

    public SimplePager() {

    }

    public SimplePager(int pageNo, int sizePerPage, int totalSize) {
        if (totalSize <= 0) {
            totalSize = 0;
        }
        this.totalSize = totalSize;

        if (sizePerPage <= 0) {
            sizePerPage = DEFAULT_SIZE_PER_PAGE;
        }
        this.sizePerPage = sizePerPage;

        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;

        if ((this.pageNo - 1) * this.sizePerPage >= totalSize) {
            this.pageNo = totalSize / this.sizePerPage;
        }
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        int totalPages = this.totalSize / this.sizePerPage;
        if (this.totalSize % this.sizePerPage != 0 || totalPages == 0) {
            totalPages++;
        }
        return totalPages;
    }

    public int getSizePerPage() {
        return this.sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public boolean isFirstPage() {
        return this.pageNo <= 1;
    }

    @Override
    public boolean isLastPage() {
        return this.pageNo >= getTotalPages();
    }

    @Override
    public int getNextPage() {
        if (isLastPage()) {
            return this.pageNo;
        } else {
            return this.pageNo + 1;
        }
    }

    @Override
    public int getPrevPage() {
        if (isFirstPage()) {
            return this.pageNo;
        } else {
            return this.pageNo - 1;
        }
    }
}
