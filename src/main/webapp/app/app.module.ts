import './vendor.ts';

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { RefplattformOneSharedModule, UserRouteAccessService } from './shared';
import { RefplattformOneAppRoutingModule} from './app-routing.module';
import { RefplattformOneHomeModule } from './home/home.module';
import { RefplattformOneAdminModule } from './admin/admin.module';
import { RefplattformOneAccountModule } from './account/account.module';
import { RefplattformOneEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ActiveMenuDirective,
    ErrorComponent
} from './layouts';

@NgModule({
    imports: [
        BrowserModule,
        RefplattformOneAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        RefplattformOneSharedModule,
        RefplattformOneHomeModule,
        RefplattformOneAdminModule,
        RefplattformOneAccountModule,
        RefplattformOneEntityModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class RefplattformOneAppModule {}
