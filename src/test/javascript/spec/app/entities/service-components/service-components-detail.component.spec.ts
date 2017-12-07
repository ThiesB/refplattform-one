/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ServiceComponentsDetailComponent } from '../../../../../../main/webapp/app/entities/service-components/service-components-detail.component';
import { ServiceComponentsService } from '../../../../../../main/webapp/app/entities/service-components/service-components.service';
import { ServiceComponents } from '../../../../../../main/webapp/app/entities/service-components/service-components.model';

describe('Component Tests', () => {

    describe('ServiceComponents Management Detail Component', () => {
        let comp: ServiceComponentsDetailComponent;
        let fixture: ComponentFixture<ServiceComponentsDetailComponent>;
        let service: ServiceComponentsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [ServiceComponentsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ServiceComponentsService,
                    JhiEventManager
                ]
            }).overrideTemplate(ServiceComponentsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ServiceComponentsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ServiceComponentsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ServiceComponents(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.serviceComponents).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
