import { BaseEntity } from './../../shared';

export class Industries implements BaseEntity {
    constructor(
        public id?: number,
        public industryName?: string,
    ) {
    }
}
