import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { DocumentTypes } from './document-types.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DocumentTypesService {

    private resourceUrl = SERVER_API_URL + 'api/document-types';

    constructor(private http: Http) { }

    create(documentTypes: DocumentTypes): Observable<DocumentTypes> {
        const copy = this.convert(documentTypes);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(documentTypes: DocumentTypes): Observable<DocumentTypes> {
        const copy = this.convert(documentTypes);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<DocumentTypes> {
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
     * Convert a returned JSON object to DocumentTypes.
     */
    private convertItemFromServer(json: any): DocumentTypes {
        const entity: DocumentTypes = Object.assign(new DocumentTypes(), json);
        return entity;
    }

    /**
     * Convert a DocumentTypes to a JSON which can be sent to the server.
     */
    private convert(documentTypes: DocumentTypes): DocumentTypes {
        const copy: DocumentTypes = Object.assign({}, documentTypes);
        return copy;
    }
}
