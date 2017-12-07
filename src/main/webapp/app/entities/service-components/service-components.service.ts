import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ServiceComponents } from './service-components.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ServiceComponentsService {

    private resourceUrl = SERVER_API_URL + 'api/service-components';

    constructor(private http: Http) { }

    create(serviceComponents: ServiceComponents): Observable<ServiceComponents> {
        const copy = this.convert(serviceComponents);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(serviceComponents: ServiceComponents): Observable<ServiceComponents> {
        const copy = this.convert(serviceComponents);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ServiceComponents> {
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
     * Convert a returned JSON object to ServiceComponents.
     */
    private convertItemFromServer(json: any): ServiceComponents {
        const entity: ServiceComponents = Object.assign(new ServiceComponents(), json);
        return entity;
    }

    /**
     * Convert a ServiceComponents to a JSON which can be sent to the server.
     */
    private convert(serviceComponents: ServiceComponents): ServiceComponents {
        const copy: ServiceComponents = Object.assign({}, serviceComponents);
        return copy;
    }
}
