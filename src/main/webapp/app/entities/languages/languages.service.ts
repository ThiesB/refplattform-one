import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Languages } from './languages.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class LanguagesService {

    private resourceUrl = SERVER_API_URL + 'api/languages';

    constructor(private http: Http) { }

    create(languages: Languages): Observable<Languages> {
        const copy = this.convert(languages);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(languages: Languages): Observable<Languages> {
        const copy = this.convert(languages);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Languages> {
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
     * Convert a returned JSON object to Languages.
     */
    private convertItemFromServer(json: any): Languages {
        const entity: Languages = Object.assign(new Languages(), json);
        return entity;
    }

    /**
     * Convert a Languages to a JSON which can be sent to the server.
     */
    private convert(languages: Languages): Languages {
        const copy: Languages = Object.assign({}, languages);
        return copy;
    }
}
