/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DocumentTypesDetailComponent } from '../../../../../../main/webapp/app/entities/document-types/document-types-detail.component';
import { DocumentTypesService } from '../../../../../../main/webapp/app/entities/document-types/document-types.service';
import { DocumentTypes } from '../../../../../../main/webapp/app/entities/document-types/document-types.model';

describe('Component Tests', () => {

    describe('DocumentTypes Management Detail Component', () => {
        let comp: DocumentTypesDetailComponent;
        let fixture: ComponentFixture<DocumentTypesDetailComponent>;
        let service: DocumentTypesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [DocumentTypesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DocumentTypesService,
                    JhiEventManager
                ]
            }).overrideTemplate(DocumentTypesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DocumentTypesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DocumentTypesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new DocumentTypes(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.documentTypes).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
