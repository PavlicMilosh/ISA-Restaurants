import { RouterModule } from '@angular/router';
import { RegisterSMComponent } from './components/registerSM/registerSM.component';
import { AddRestaurantComponent } from './components/addRestaurant/addRestaurant.component';
var appRoutes = [
    {
        path: '',
        component: RegisterSMComponent
    },
    {
        path: 'registerSM',
        component: RegisterSMComponent
    },
    {
        path: 'addRestaurant',
        component: AddRestaurantComponent
    }
];
export var routing = RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map