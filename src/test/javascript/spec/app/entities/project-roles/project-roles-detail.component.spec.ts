/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { RefplattformOneTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProjectRolesDetailComponent } from '../../../../../../main/webapp/app/entities/project-roles/project-roles-detail.component';
import { ProjectRolesService } from '../../../../../../main/webapp/app/entities/project-roles/project-roles.service';
import { ProjectRoles } from '../../../../../../main/webapp/app/entities/project-roles/project-roles.model';

describe('Component Tests', () => {

    describe('ProjectRoles Management Detail Component', () => {
        let comp: ProjectRolesDetailComponent;
        let fixture: ComponentFixture<ProjectRolesDetailComponent>;
        let service: ProjectRolesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [RefplattformOneTestModule],
                declarations: [ProjectRolesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProjectRolesService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProjectRolesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProjectRolesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProjectRolesService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ProjectRoles(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.projectRoles).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
