import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ConsultingDivision } from './consulting-division.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ConsultingDivisionService {

    private resourceUrl = SERVER_API_URL + 'api/consulting-divisions';

    constructor(private http: Http) { }

    create(consultingDivision: ConsultingDivision): Observable<ConsultingDivision> {
        const copy = this.convert(consultingDivision);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(consultingDivision: ConsultingDivision): Observable<ConsultingDivision> {
        const copy = this.convert(consultingDivision);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ConsultingDivision> {
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
     * Convert a returned JSON object to ConsultingDivision.
     */
    private convertItemFromServer(json: any): ConsultingDivision {
        const entity: ConsultingDivision = Object.assign(new ConsultingDivision(), json);
        return entity;
    }

    /**
     * Convert a ConsultingDivision to a JSON which can be sent to the server.
     */
    private convert(consultingDivision: ConsultingDivision): ConsultingDivision {
        const copy: ConsultingDivision = Object.assign({}, consultingDivision);
        return copy;
    }
}
