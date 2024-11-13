import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./pages/home/home.component";
import { AboutComponent } from "./pages/about/about.component";
import { AddRecordsComponent } from "./pages/records/add-records/add-records.component";
import { GetRecordsComponent } from "./pages/records/get-records/get-records.component";
import { UpdateRecordsComponent } from "./pages/records/update-records/update-records.component";
import { DeleteRecordsComponent } from "./pages/records/delete-records/delete-records.component";
import { MainLayoutComponent } from "../shared/components/main-layout/main-layout.component";


//Este archivo es para manejar las rutas lazy load de la aplicación

const routes: Routes = [
    {
        path: '',
        component: MainLayoutComponent,
        children: [
            {
                path: '',
                component: HomeComponent
            },
            {
                path: 'about',
                component: AboutComponent
            },
            {
                path: 'records',
                children: [
                    {
                        path: 'add',
                        component: AddRecordsComponent
                    },
                    {
                        path: 'get',
                        component: GetRecordsComponent
                    },
                    {
                        path: 'update',
                        component: UpdateRecordsComponent
                    },
                    {
                        path: 'delete',
                        component: DeleteRecordsComponent
                    }
                ]
            }
        ]
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Semana3RoutingModule { }