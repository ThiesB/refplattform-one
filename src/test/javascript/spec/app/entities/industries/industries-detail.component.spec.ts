/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { IndustriesDetailComponent } from '../../../../../../main/webapp/app/entities/industries/industries-detail.component';
import { IndustriesService } from '../../../../../../main/webapp/app/entities/industries/industries.service';
import { Industries } from '../../../../../../main/webapp/app/entities/industries/industries.model';

describe('Component Tests', () => {

    describe('Industries Management Detail Component', () => {
        let comp: IndustriesDetailComponent;
        let fixture: ComponentFixture<IndustriesDetailComponent>;
        let service: IndustriesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [IndustriesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    IndustriesService,
                    JhiEventManager
                ]
            }).overrideTemplate(IndustriesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IndustriesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndustriesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Industries(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.industries).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
