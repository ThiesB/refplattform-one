import { BaseEntity } from './../../shared';

export class Customers implements BaseEntity {
    constructor(
        public id?: number,
        public customerName?: string,
    ) {
    }
}
