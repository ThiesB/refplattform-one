import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerReferences } from './customer-references.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CustomerReferencesService {

    private resourceUrl = SERVER_API_URL + 'api/customer-references';

    constructor(private http: Http) { }

    create(customerReferences: CustomerReferences): Observable<CustomerReferences> {
        const copy = this.convert(customerReferences);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(customerReferences: CustomerReferences): Observable<CustomerReferences> {
        const copy = this.convert(customerReferences);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<CustomerReferences> {
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
     * Convert a returned JSON object to CustomerReferences.
     */
    private convertItemFromServer(json: any): CustomerReferences {
        const entity: CustomerReferences = Object.assign(new CustomerReferences(), json);
        return entity;
    }

    /**
     * Convert a CustomerReferences to a JSON which can be sent to the server.
     */
    private convert(customerReferences: CustomerReferences): CustomerReferences {
        const copy: CustomerReferences = Object.assign({}, customerReferences);
        return copy;
    }
}
