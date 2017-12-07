import { BaseEntity } from './../../shared';

export class Downloads implements BaseEntity {
    constructor(
        public id?: number,
        public titel?: string,
        public bereich?: string,
        public anlageURL?: string,
        public customerReferencesId?: number,
        public doctypeId?: number,
        public languageId?: number,
    ) {
    }
}
