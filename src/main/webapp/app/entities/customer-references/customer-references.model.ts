import { BaseEntity } from './../../shared';

export class CustomerReferences implements BaseEntity {
    constructor(
        public id?: number,
        public titel?: string,
        public projectTimeSpan?: number,
        public projectVolume?: number,
        public projectTeam?: string,
        public exxetaConsultingAmount?: number,
        public referenceOwner?: string,
        public exxetaProject?: boolean,
        public finished?: boolean,
        public schlagworte?: string,
        public contact?: string,
        public kritischeErfolgsfaktoren?: string,
        public anmerkungen?: string,
        public downloads?: BaseEntity[],
        public consultingdivisionId?: number,
        public customerId?: number,
        public industryId?: number,
        public projectroles?: BaseEntity[],
        public servicecomponents?: BaseEntity[],
    ) {
        this.exxetaProject = false;
        this.finished = false;
    }
}
