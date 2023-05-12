import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UploadResult } from "../models";
import { Observable, firstValueFrom } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class UploadService{
    
    constructor(private http: HttpClient){ }

    upload(result: UploadResult) {
        const toSend = new FormData()
        toSend.append('name', result.name)
        toSend.append('title', result.title)
        toSend.append('comments', result.comments)
        toSend.append('archive', result.archive)

        firstValueFrom(this.http.post('http://localhost:8080/upload', toSend)).then(() => console.log("done")).catch(() => console.log("error"))
    }
}