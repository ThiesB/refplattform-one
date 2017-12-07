/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { LanguagesDetailComponent } from '../../../../../../main/webapp/app/entities/languages/languages-detail.component';
import { LanguagesService } from '../../../../../../main/webapp/app/entities/languages/languages.service';
import { Languages } from '../../../../../../main/webapp/app/entities/languages/languages.model';

describe('Component Tests', () => {

    describe('Languages Management Detail Component', () => {
        let comp: LanguagesDetailComponent;
        let fixture: ComponentFixture<LanguagesDetailComponent>;
        let service: LanguagesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [LanguagesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    LanguagesService,
                    JhiEventManager
                ]
            }).overrideTemplate(LanguagesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LanguagesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LanguagesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Languages(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.languages).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
