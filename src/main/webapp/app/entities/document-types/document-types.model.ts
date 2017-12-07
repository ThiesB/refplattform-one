import { BaseEntity } from './../../shared';

export class DocumentTypes implements BaseEntity {
    constructor(
        public id?: number,
        public titel?: string,
        public type?: string,
    ) {
    }
}
