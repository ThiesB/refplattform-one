/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomersDetailComponent } from '../../../../../../main/webapp/app/entities/customers/customers-detail.component';
import { CustomersService } from '../../../../../../main/webapp/app/entities/customers/customers.service';
import { Customers } from '../../../../../../main/webapp/app/entities/customers/customers.model';

describe('Component Tests', () => {

    describe('Customers Management Detail Component', () => {
        let comp: CustomersDetailComponent;
        let fixture: ComponentFixture<CustomersDetailComponent>;
        let service: CustomersService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [CustomersDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomersService,
                    JhiEventManager
                ]
            }).overrideTemplate(CustomersDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomersDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomersService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Customers(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customers).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
