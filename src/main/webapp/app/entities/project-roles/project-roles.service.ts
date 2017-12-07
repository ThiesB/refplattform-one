import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { ProjectRoles } from './project-roles.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ProjectRolesService {

    private resourceUrl = SERVER_API_URL + 'api/project-roles';

    constructor(private http: Http) { }

    create(projectRoles: ProjectRoles): Observable<ProjectRoles> {
        const copy = this.convert(projectRoles);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(projectRoles: ProjectRoles): Observable<ProjectRoles> {
        const copy = this.convert(projectRoles);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<ProjectRoles> {
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
     * Convert a returned JSON object to ProjectRoles.
     */
    private convertItemFromServer(json: any): ProjectRoles {
        const entity: ProjectRoles = Object.assign(new ProjectRoles(), json);
        return entity;
    }

    /**
     * Convert a ProjectRoles to a JSON which can be sent to the server.
     */
    private convert(projectRoles: ProjectRoles): ProjectRoles {
        const copy: ProjectRoles = Object.assign({}, projectRoles);
        return copy;
    }
}
