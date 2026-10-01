import { Routes } from '@angular/router';

import { Login } from './auth/login/login';
import { Signup } from './auth/signup/signup';

import { Crops } from './pages/crops/crops';
import { Dashboard } from './pages/dashboard/dashboard';
import { CropPlan } from './pages/crop-plan/crop-plan';
import { Field } from './pages/field/field';
import { FarmProfile } from './pages/farm-profile/farm-profile';
import { Activity } from './pages/activity/activity';
import { Expense } from './pages/expense/expense';
import { Harvest } from './pages/harvest/harvest';
import { Equipment } from './pages/equipment/equipment';
import { Maintenance } from './pages/maintenance/maintenance';
import { Rotation } from './pages/rotation/rotation';
import { authGuard } from './auth/auth-guard';

export const routes: Routes = [

{
path: '',
redirectTo: 'login',
pathMatch: 'full'
},

{
path: 'login',
component: Login
},

{
path: 'signup',
component: Signup
},

{
  path: 'dashboard',
  component: Dashboard,
  canActivate: [authGuard]
},
{
  path: 'crops',
  component: Crops,
  canActivate: [authGuard]
},
{
  path: 'crop-plans',
  component: CropPlan,
  canActivate: [authGuard]
},
{
  path: 'fields',
  component: Field,
  canActivate: [authGuard]
},
{
  path: 'farm-profile',
  component: FarmProfile,
  canActivate: [authGuard]
},
{
  path: 'activities',
  component: Activity,
  canActivate: [authGuard]
},
{
  path: 'expenses',
  component: Expense,
  canActivate: [authGuard]
},
{
  path: 'harvest',
  component: Harvest,
  canActivate: [authGuard]
},
{
  path: 'equipment',
  component: Equipment,
  canActivate: [authGuard]
},
{
  path: 'maintenance',
  component: Maintenance,
  canActivate: [authGuard]
},
{
  path: 'rotation',
  component: Rotation,
  canActivate: [authGuard]
}

];
