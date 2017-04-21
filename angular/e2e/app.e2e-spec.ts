import { IsaAppPage } from './app.po';

describe('isa-app App', () => {
  let page: IsaAppPage;

  beforeEach(() => {
    page = new IsaAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
