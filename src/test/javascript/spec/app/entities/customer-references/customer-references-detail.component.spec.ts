/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomerReferencesDetailComponent } from '../../../../../../main/webapp/app/entities/customer-references/customer-references-detail.component';
import { CustomerReferencesService } from '../../../../../../main/webapp/app/entities/customer-references/customer-references.service';
import { CustomerReferences } from '../../../../../../main/webapp/app/entities/customer-references/customer-references.model';

describe('Component Tests', () => {

    describe('CustomerReferences Management Detail Component', () => {
        let comp: CustomerReferencesDetailComponent;
        let fixture: ComponentFixture<CustomerReferencesDetailComponent>;
        let service: CustomerReferencesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [CustomerReferencesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomerReferencesService,
                    JhiEventManager
                ]
            }).overrideTemplate(CustomerReferencesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerReferencesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerReferencesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CustomerReferences(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customerReferences).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
