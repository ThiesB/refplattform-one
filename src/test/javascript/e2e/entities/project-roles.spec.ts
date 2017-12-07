import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('ProjectRoles e2e test', () => {

    let navBarPage: NavBarPage;
    let projectRolesDialogPage: ProjectRolesDialogPage;
    let projectRolesComponentsPage: ProjectRolesComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ProjectRoles', () => {
        navBarPage.goToEntity('project-roles');
        projectRolesComponentsPage = new ProjectRolesComponentsPage();
        expect(projectRolesComponentsPage.getTitle()).toMatch(/refplattformOneApp.projectRoles.home.title/);

    });

    it('should load create ProjectRoles dialog', () => {
        projectRolesComponentsPage.clickOnCreateButton();
        projectRolesDialogPage = new ProjectRolesDialogPage();
        expect(projectRolesDialogPage.getModalTitle()).toMatch(/refplattformOneApp.projectRoles.home.createOrEditLabel/);
        projectRolesDialogPage.close();
    });

    it('should create and save ProjectRoles', () => {
        projectRolesComponentsPage.clickOnCreateButton();
        projectRolesDialogPage.setRoleNameInput('roleName');
        expect(projectRolesDialogPage.getRoleNameInput()).toMatch('roleName');
        projectRolesDialogPage.save();
        expect(projectRolesDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class ProjectRolesComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-project-roles div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ProjectRolesDialogPage {
    modalTitle = element(by.css('h4#myProjectRolesLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    roleNameInput = element(by.css('input#field_roleName'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setRoleNameInput = function (roleName) {
        this.roleNameInput.sendKeys(roleName);
    }

    getRoleNameInput = function () {
        return this.roleNameInput.getAttribute('value');
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
