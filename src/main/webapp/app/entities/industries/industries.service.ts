import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Industries } from './industries.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class IndustriesService {

    private resourceUrl = SERVER_API_URL + 'api/industries';

    constructor(private http: Http) { }

    create(industries: Industries): Observable<Industries> {
        const copy = this.convert(industries);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(industries: Industries): Observable<Industries> {
        const copy = this.convert(industries);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Industries> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Industries.
     */
    private convertItemFromServer(json: any): Industries {
        const entity: Industries = Object.assign(new Industries(), json);
        return entity;
    }

    /**
     * Convert a Industries to a JSON which can be sent to the server.
     */
    private convert(industries: Industries): Industries {
        const copy: Industries = Object.assign({}, industries);
        return copy;
    }
}
