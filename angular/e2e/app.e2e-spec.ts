import { AngularRestauratnsPage } from './app.po';

describe('angular-restauratns App', () => {
  let page: AngularRestauratnsPage;

  beforeEach(() => {
    page = new AngularRestauratnsPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
