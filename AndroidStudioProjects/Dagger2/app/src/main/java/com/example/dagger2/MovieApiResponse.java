package com.example.dagger2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieApiResponse {

  private long page;

  @SerializedName("total_pages")
  private long totalPages;

  @SerializedName("total_results")
  private long totalResults;

  private List<MovieEntity> results;


  public List<MovieEntity> getResults() {
    return results;
  }

  public void setResults(List<MovieEntity> results) {
    this.results = results;
  }

  public long getPage() {
    return page;
  }

  public void setPage(long page) {
    this.page = page;
  }

  public long getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(long totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(long totalResults) {
    this.totalResults = totalResults;
  }
}
