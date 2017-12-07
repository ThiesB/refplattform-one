/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DownloadsDetailComponent } from '../../../../../../main/webapp/app/entities/downloads/downloads-detail.component';
import { DownloadsService } from '../../../../../../main/webapp/app/entities/downloads/downloads.service';
import { Downloads } from '../../../../../../main/webapp/app/entities/downloads/downloads.model';

describe('Component Tests', () => {

    describe('Downloads Management Detail Component', () => {
        let comp: DownloadsDetailComponent;
        let fixture: ComponentFixture<DownloadsDetailComponent>;
        let service: DownloadsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [DownloadsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DownloadsService,
                    JhiEventManager
                ]
            }).overrideTemplate(DownloadsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DownloadsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DownloadsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Downloads(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.downloads).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
