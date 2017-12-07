import { BaseEntity } from './../../shared';

export class ProjectRoles implements BaseEntity {
    constructor(
        public id?: number,
        public roleName?: string,
    ) {
    }
}
