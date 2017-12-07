import { BaseEntity } from './../../shared';

export class ServiceComponents implements BaseEntity {
    constructor(
        public id?: number,
        public serviceName?: string,
        public consultingdivisionId?: number,
    ) {
    }
}
