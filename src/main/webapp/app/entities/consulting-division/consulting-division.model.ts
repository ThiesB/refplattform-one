import { BaseEntity } from './../../shared';

export class ConsultingDivision implements BaseEntity {
    constructor(
        public id?: number,
        public divisionName?: string,
    ) {
    }
}
