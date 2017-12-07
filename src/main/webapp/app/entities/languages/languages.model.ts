import { BaseEntity } from './../../shared';

export class Languages implements BaseEntity {
    constructor(
        public id?: number,
        public languageName?: string,
    ) {
    }
}
