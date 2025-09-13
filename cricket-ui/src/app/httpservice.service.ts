import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HttpserviceService {

  constructor(private http: HttpClient,private router: Router) { }
  
 context = 'http://18.222.135.44:8085/';
  post(url: any, request: any) {
    // return this.http.post(this.context+url, request);
    console.log(  "the ppost url is ",this.context+url)
    if (request instanceof FormData) {
      return this.http.post(this.context+url, request, {
        reportProgress: true,
        observe: 'events'
      });
    } else {

      return this.http.post(this.context + url, request);
    }
  
  }
  
delete(url: any, request: any) {
  const apiUrl = this.context + url;
  if (request instanceof FormData) {
    return this.http.delete(apiUrl, {
      reportProgress: true,
      observe: 'events'
    });
  } else {
    return this.http.delete(apiUrl, { params: request });
  }
}

put(url: any, request: any) {
    return this.http.put(this.context+url, request);
  }
  get(url: any) {
    const fullPath = `${this.context}${url}`;
  console.log('Requesting FULL URL:', fullPath);
    return this.http.get(this.context+url);
  }
}
